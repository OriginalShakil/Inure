package app.simple.inure.preferences

import android.annotation.SuppressLint
import app.simple.inure.util.AppUtils
import app.simple.inure.util.CalendarUtils
import java.util.Date

@Suppress("NOTHING_TO_INLINE", "UseKtx")
object TrialPreferences {

    const val MAX_TRIAL_DAYS = 0xF

    private const val FIRST_LAUNCH = "first_launch_"
    const val IS_APP_FULL_VERSION_ENABLED = "is_full_version_"
    private const val IS_LEGACY_MIGRATED = "is_legacy_migrated_"
    private const val IS_UNLOCKER_VERIFICATION_REQUIRED = "is_unlocker_verification_required_"
    private const val LAST_VERIFICATION_DATE = "last_verification_date_"

    const val HAS_LICENSE_KEY = "has_license_key"

    // ---------------------------------------------------------------------------------------------------------- //

    @SuppressLint("UseKtx")
    fun setFirstLaunchDate(date: Long) {
    }

    fun getFirstLaunchDate(): Long {
        return System.currentTimeMillis()
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun getDaysLeft(): Int {
        return 0
    }

    fun getMaxDays(): Int {
        return MAX_TRIAL_DAYS
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setFullVersion(value: Boolean): Boolean {
        return true
    }

    inline fun isAppFullVersionEnabled(): Boolean {
        return true
    }

    fun isWithinTrialPeriod(): Boolean {
        return true
    }

    fun isTrialWithoutFull(): Boolean {
        return false
    }

    fun isFullVersion(): Boolean {
        return true
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun reset() {
    }

    fun migrateLegacy() {
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setLegacyMigrated(value: Boolean) {
    }

    private fun isLegacyMigrated(): Boolean {
        return true
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setHasLicenceKey(hasLicence: Boolean) {
    }

    fun hasLicenceKey(): Boolean {
        return true
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setUnlockerVerificationRequired(value: Boolean): Boolean {
        return true
    }

    fun isUnlockerVerificationRequired(): Boolean {
        return false
    }

    // ---------------------------------------------------------------------------------------------------------- //

    fun setLastVerificationDate(date: Long) {
    }

    fun getLastVerificationDate(): Long {
        return System.currentTimeMillis()
    }
}
