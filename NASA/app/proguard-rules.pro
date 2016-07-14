-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-keepattributes SourceFile,LineNumberTable,Signature,RuntimeVisibleAnnotations
-keepattributes *Annotation*,EnclosingMethod

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class com.crittercism.**
-keep class com.badlogic.**
-keep public class com.google.code.linkedinapi.**
-keep class com.google.**
-keep public class * implements java.lang.reflect.Type
-keep class java.lang.reflect.**
-keep class com.facebook.** {*;}
-keep class javax.**  { *; }
-keep class org.**  { *; }
-keep class twitter4j.**  { *; }
-keep class java.lang.management.**  { *; }
-keep class com.google.** {*;}
-keepclassmembers class com.google.** {*;}
-keep class oauth.signpost.**  { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.j256.**
-keep class org.codehaus.** { *; }
-keep class org.apache.commons.** { *; }
-keep class org.w3c.** { *; }
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }
-keepclassmembers class **.model.** { *; }
-keep public class **.model.** {
  public void set*(***);
  public *** get*();
}
-keep public class android.app.ActivityTransitionCoordinator


-keep public class com.androidquery.*,com.androidquery.callback.*,com.androidquery.util.AQUtility,com.androidquery.util.Constants
-keep public class *{
    public protected *;
}
-keepclassmembers public class com.crittercism.*{*;}


-dontwarn twitter4j.**
-dontwarn javax.xml.**
-dontwarn javax.xml.stream.events.**
-dontwarn com.fasterxml.jackson.databind.**
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry
-dontwarn org.apache.**
-dontwarn oauth.signpost.**
-dontwarn android.support.v4.**

-keepnames class org.codehaus.jackson.** { *; }

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keepclassmembers public final enum org.codehaus.jackson.annotate.JsonAutoDetect$Visibility {
    public static final org.codehaus.jackson.annotate.JsonAutoDetect$Visibility *;
}

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep class com.google.android.gms.ads.identifier.** { *; }

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class * implements java.io.Serializable

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Allow obfuscation of android.support.v7.internal.view.menu.**
# to avoid problem on Samsung 4.2.2 devices with appcompat v21
# see https://code.google.com/p/android/issues/detail?id=78377
-keep class !android.support.v7.internal.view.menu.**,android.support.** {*;}

# Retrofit 1.X

-keep class com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn com.squareup.okhttp.**f
-dontwarn okio.**
-dontwarn retrofit.**
-dontwarn rx.**

-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

# If in your rest service interface you use methods with Callback argument.
-keepattributes Exceptions

# If your rest service methods throw custom exceptions, because you've defined an ErrorHandler.
-keepattributes Signature

