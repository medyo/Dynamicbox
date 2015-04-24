Dynamicbox
========== 
![Icon](https://raw.github.com/medyo/dynamicbox/master/screenshots/cat-box-icon.png "Dynamic Box")  

DynamicBox is a library which inflates custom layouts to indicate : 
 
* loading content
* show an exception
* or even a custom view.  

The philosophy behind this library is to improve the UX through informing the user about what's happening behind, if the data is loading or an exception is thrown while fetching data...

Screenshots
---
 
![demo 1](https://raw.github.com/medyo/dynamicbox/master/screenshots/1.png)
![demo 4](https://raw.github.com/medyo/dynamicbox/master/screenshots/4.png)
![demo 5](https://raw.github.com/medyo/dynamicbox/master/screenshots/5.png)
![demo 2](https://raw.github.com/medyo/dynamicbox/master/screenshots/2.png)
![demo 3](https://raw.github.com/medyo/dynamicbox/master/screenshots/3.png)

__Supports:__

- `ListView`
- `GridView`
- `ExpandableListView`
- `Activity`
- `FragmentActivity`
- `Fragment`
- `LinearLayout`
- `RelativeLayout`
- `ScrollView`
- `FrameLayout`

Including in your project : Maven Central
---------------------

```compile 'com.github.medyo:dynamicbox:1.0@aar'```

Usage
---------------------

```
DynamicBox box = new DynamicBox(this,view);
```
*`this` : refers to the current Activity*  
*`view` : refers to the target view, eg a ListView or a layout*

Example
---------------------

- View

```
ListView lv = (ListView)findViewById(R.id.listView);    
DynamicBox box = new DynamicBox(this,lv);
```

- Layout id

```
DynamicBox box = new DynamicBox(this,R.layout.activity_activity);
```
_____

**To Show the loading View**  
```
box.showLoadingLayout();
```

**To Show Internet off View**  
```
box.showInternetOffLayout();
```

**To Show Exception View**  
```
box.showExceptionLayout();
```

**To Show a Custom View**  
```
View customView = getLayoutInflater().inflate(R.layout.custom_view, null, false);
box.addCustomView(customView,"greenmonster");
box.showCustomView("greenmonster");
```  

**To set Loading Message**
```
box.setLoadingMessage("Loading your music ...");
```
or by overriding [strings.xml variables](DynamicBoxExample/src/main/res/values/strings.xml)

See the example project for more details : [Sample](DynamicBoxExample/src/main/java/mehdi/sakout/dynamicboxexample/)

Apps using DynamicBox  
---------------------   
[![ElBotola](https://raw.github.com/medyo/dynamicbox/master/screenshots/apps/com.mobiacube.elbotola.png)](https://play.google.com/store/apps/details?id=com.mobiacube.elbotola)  

Feel free to shoot me an email if your app is using it

Developed By
---------------------
El Mehdi Sakout <elmehdi.sakout@gmail.com>

Resources
---------------------
Thanks to [IconKa](http://www.iconka.com) for the cat icon.

License
---------------------

MIT  
[http://opensource.org/licenses/MIT](http://opensource.org/licenses/MIT)