# Simple Bitcoin Application
## Introduction
Simple Bitcoin Application is created to view and analyze changes in Bitcoin value against USD in the last year. A simple UI is designed for application to make it user-friendly and easy to use. User can zoom in/out and move in the graph to see more close changes and recent values. 

## Application Notes
I used RoomDB like a cache in this application. Therefore it is the truth point, which is I show data from the local database. If local database is empty, system fetches from remote data source and transfers to local database.

I used ktlint for code formatting so that my code will be clean and coherent with the official kotlin coding convention.

* Flow:
<p float="left">
  <img src="https://github.com/yilmazvolkan/simpleBitcoinApp/blob/ui-updates/screenshots/flow.png" height="200">
</p>

## To Do
If I have enough time,

* I would create test cases with Mockito and Expresso for repository class, view model and UIs.
* I would try multi modular structure for each feature.

## Future Work
I determined some future works for the project.

* Design a system to get 1 month and 1 week values from the same table to show user in a different charts with user manual selection above the chart.
* Design a system to show other crypto coins in a recyclerview as a list such as Etherium, Tether, Litecoin etc.
* Design a system to fetch each coin current values and show it to user with ask/bid price on the screen.


## Insight from the application
<p float="left">
  <img src="https://github.com/yilmazvolkan/simpleBitcoinApp/blob/ui-updates/screenshots/data_loaded_state.png" width="150">
  <img src="https://github.com/yilmazvolkan/simpleBitcoinApp/blob/ui-updates/screenshots/zoom_in_state.png" width="150">
  <img src="https://github.com/yilmazvolkan/simpleBitcoinApp/blob/ui-updates/screenshots/error_state.png" width="150">
</p>

## Local Development

* Min sdk: 21
* Andoid Studio 4.1.1


## Libraries Used

  * [Data Binding][0] - Declaratively bind observable data to UI elements.
  * [LiveData][1] - Build data objects that notify views when the underlying database changes.
  * [Room][2] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [Ktlint][3] - Analyze source code to flag programming or stylistic errors.
  * [MPAndroidChart][4] - Build Android charts and chart objects for view.
  * [Retrofit2][5] - REST Client for Java and Android to retrieve JSON.
  * [RxJava2][6] - Threading operations.

[0]: https://developer.android.com/topic/libraries/data-binding/
[1]: https://developer.android.com/topic/libraries/architecture/livedata
[2]: https://developer.android.com/topic/libraries/architecture/room
[3]: https://ktlint.github.io
[4]: https://github.com/PhilJay/MPAndroidChart
[5]: https://square.github.io/retrofit/
[6]: https://github.com/ReactiveX/RxAndroid

## Resources
Blockchain Charts API Documentation on this [link](https://blockchain.info/api/charts_api).

Blockchain Market Price Graph on this [link](https://api.blockchain.info/charts/market-price).
