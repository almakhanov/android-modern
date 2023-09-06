package kz.veter420.android_modern.utils


fun nameToInitial(fullName: String): String {
    val words = fullName.split(" ")
    val firstLetter = words.getOrNull(0)?.getOrNull(0)?.uppercase() ?: '?'
    val secondLetter = words.getOrNull(1)?.getOrNull(0)?.uppercase() ?: '?'
    return "$firstLetter$secondLetter"
}
