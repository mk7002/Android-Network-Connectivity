
# Android-Network-Connectivity
Android Networking Library listening for the Internet connectivity.

[![](https://jitpack.io/v/mk7002/Android-Network-Connectivity.svg)](https://jitpack.io/#mk7002/Android-Network-Connectivity)



## Getting Started

These instructions will help you set up this library easily on your current project and working in no time. You only need a few configurations to start working!

## Installing

To be able to use the following library, you will need to add the following gradle dependency in your
#### build.gradle Project level

```css
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

#### build.gradle  module

```css
implementation 'com.github.mk7002:Android-Network-Connectivity:Tag'
```
That is the basic set up needed to be able to use the library in your applications!

## Usage

In your Activity
```java


public class MainActivity extends AppCompatActivity {  
  
    Disposable disposable;  
  
  @Override  
  protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_main);  
		disposable = Network  
                .observeInternetConnectivity()  
                .subscribeOn(Schedulers.io())  
                .observeOn(AndroidSchedulers.mainThread())  
                .subscribe(connected -> {  
	                     
				  });  
  }  
  
    @Override  
  protected void onDestroy() {  
         super.onDestroy();  
		 if (disposable != null)  
            disposable.dispose();  
  }  
}
```
You can also customize the network settings
```java
NetworkSettings settings=new NetworkSettings.Builder()  
        .setDEFAULT_HOST("www.google.com")  
        .setDEFAULT_TIMEOUT_IN_MILLIS(2500)  
        .setHttpResponse(1000)  
        .setInterval(1000)  
        .setInterval(1)  
        .build(); 
         
	disposable = Network  
        .observeInternetConnectivity(settings)  
        .subscribeOn(Schedulers.io())  
        .observeOn(AndroidSchedulers.mainThread())  
        .subscribe(connected -> {  
            
		  });
```
If you get any error relate to rxjava , then add RxJava library inside your build.gradle(module) file 
```css
 	implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    	implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
```
