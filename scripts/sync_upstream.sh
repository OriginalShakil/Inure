#!/usr/bin/env bash
set -e

# ==============================================================================
# Inure Upstream Sync Script
# Updates the local repository with latest upstream changes while preserving
# the full-version unlock and zero-trial modifications.
# ==============================================================================

UPSTREAM_URL="https://github.com/Hamza417/Inure.git"
UPSTREAM_BRANCH="master"
PATCH_FILE="patches/unlock_full_version.patch"

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$REPO_ROOT"

echo "==> Checking upstream remote..."
if ! git remote | grep -q "^upstream$"; then
    echo "    Adding upstream remote ($UPSTREAM_URL)..."
    git remote add upstream "$UPSTREAM_URL"
else
    echo "    Upstream remote already configured."
fi

echo "==> Fetching updates from upstream/$UPSTREAM_BRANCH..."
git fetch upstream "$UPSTREAM_BRANCH"

CURRENT_BRANCH="$(git rev-parse --abbrev-ref HEAD)"
echo "==> Current branch: $CURRENT_BRANCH"

echo "==> Merging upstream/$UPSTREAM_BRANCH into $CURRENT_BRANCH..."
if git merge "upstream/$UPSTREAM_BRANCH" --no-edit; then
    echo "    Merge succeeded cleanly."
else
    echo "==> Merge had conflicts in TrialPreferences.kt, resolving using patch..."
    git checkout --ours app/src/main/java/app/simple/inure/preferences/TrialPreferences.kt 2>/dev/null || true
    if git apply "$PATCH_FILE"; then
        git add app/src/main/java/app/simple/inure/preferences/TrialPreferences.kt
        git commit -m "chore: re-apply full version unlock after upstream merge"
        echo "    Successfully resolved and committed."
    else
        echo "    Automatic patch resolution failed. Please inspect git status."
        exit 1
    fi
fi

# Ensure patch is applied if upstream replaced TrialPreferences.kt
if ! grep -q "fun isAppFullVersionEnabled(): Boolean {" app/src/main/java/app/simple/inure/preferences/TrialPreferences.kt; then
    echo "==> Applying unlock patch..."
    git apply "$PATCH_FILE"
fi

echo "==> Sync complete! Repository is up-to-date with upstream and full version remains unlocked."
