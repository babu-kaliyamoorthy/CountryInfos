# Android JSON Parser Demo App: Developed By Babu Kaliyamoorthy


This repository contains a detailed sample app that implements MVVM architecture using ViewModel,LiveData,
View , Model and DataBinding .

<br/>1.This App parse the JSON data from a web service API.
<br/>2.App is designed using MVVM pattern and Android architecture components ViewModel, LiveData and DataBinding.
<br/>3.Developed App in Kotlin with Coroutines API.
<br/>4.App supports both portrait and landscape modes.
<br/>5.Written Unit test cases for the modules.


<p align="center">
  <img src="https://user-images.githubusercontent.com/5718428/70892543-663b9300-200f-11ea-99df-981364fe5613.jpg" width="250">

  <img src="https://user-images.githubusercontent.com/5718428/70892596-7eabad80-200f-11ea-9edd-2b69e6ce5989.jpg" width="250">
  <img src="https://user-images.githubusercontent.com/5718428/70892662-997e2200-200f-11ea-9184-2d40ce30f6dd.jpg" width="250">
</p>
<br>

#### The app has following packages:
1. **data**: model classes contains the classes like data class,repository accessing classes ,etc.
2. **ui**: View classes along with their corresponding ViewModel.
3. **utils**: Utility classes.
4. **di**: dependency injection classes.
5. **adapter**: Adapter class is available to bind the data to the recyclerview.


#### Classes have been designed in such a way that it could be inherited and maximize the code reuse.

### Library and Concept reference resources:
1. LiveData: https://developer.android.com/topic/libraries/architecture/livedata
2. ViewModel: https://developer.android.com/topic/libraries/architecture/viewmodel
3. DataBinding: https://developer.android.com/topic/libraries/data-binding
3. Coroutines: https://kotlinlang.org/docs/reference/coroutines-overview.html


### License
```
   Copyright (C) 2017 TELSTRA LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
