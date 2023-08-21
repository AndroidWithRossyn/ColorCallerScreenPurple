buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jetbrains.kotlin.android") version "1.7.21" apply false
    base // adds clean task to root project
}
//id("org.jetbrains.kotlin.android") version "1.6.10" apply false
subprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
        maven { url = uri("https://www.jitpack.io" ) }
    }


//    buildscript {
//        repositories {
//            gradlePluginPortal()
//        }
//        dependencies {
////            classpath("com.android.tools.build:gradle:7.1.1")
////            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
////            classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
//            classpath("com.google.gms:google-services:4.3.10")
//            classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
//
//        }
//    }
}