


<p align="center">
 
 <h2 align="center">Compose TransformingPager.</h2>
 <p align="center"><b>Compose pager with effects.</b></p>
 <p align="center">
    <img width="150px" src="https://github.com/kiselyv77/TransformingPager/blob/master/gifs/Cube.gif"align="center"/>
    <img width="150px" src="https://github.com/kiselyv77/TransformingPager/blob/master/gifs/Flip.gif"align="center"/>
    <img width="150px" src="https://github.com/kiselyv77/TransformingPager/blob/master/gifs/Rotate.gif"align="center"/>
    <img width="150px" src="https://github.com/kiselyv77/TransformingPager/blob/master/gifs/Zoom.gif"align="center"/>
    <img width="150px" src="https://github.com/kiselyv77/TransformingPager/blob/master/gifs/Compression.gif"align="center"/>
    <img width="150px" src="https://github.com/kiselyv77/TransformingPager/blob/master/gifs/Scale.gif"align="center"/>
</p>
 
</p>

🛠Gradle
------
gradle.settings
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url 'https://jitpack.io' }
    }
}
```
gradle.app
```gradle
dependencies {
implementation 'com.github.kiselyv77:TransformingPager:1.0'
}
```

💥Usage
------

```kotlin
TransformingHorizontalPager(
        count = 100,
        pagerType = PagerType.Cube
    ) { page ->
        //Content
  }
  
  TransformingVerticalPager(
        count = 100,
        pagerType = PagerType.Rotate(rotationValue = 45f)
    ) { page ->
        //Content
  }
```

 ## 🤝 License
```
The MIT License
Copyright (c) 2016-2020 Mahach Imangazaliev
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```




