package kz.veter420.android_modern.utils


fun formatPhoneNumber(phoneNumber: String): String {
    val countryCode = "+7"
    val areaCode = phoneNumber.substring(0, 3)
    val firstPart = phoneNumber.substring(3, 6)
    val secondPart = phoneNumber.substring(6, 8)
    val thirdPart = phoneNumber.substring(8)
    return "$countryCode ($areaCode) $firstPart-$secondPart-$thirdPart"
}
