# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep classes used by Moshi reflection
-keep class ch.aso.capture.logger.** { *; }

# OpenCV
-keep class org.opencv.** { *; }

# ARCore
-keep class com.google.ar.** { *; }
