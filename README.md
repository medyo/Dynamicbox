Dynamicbox
========== 

# Deprecated, Please refer the new implementation at: https://github.com/medyo/StateViews

![Icon](https://raw.github.com/medyo/dynamicbox/master/screenshots/cat-box-icon.png "Dynamic Box")  

DynamicBox is a library which inflates custom layouts to indicate : 
 
* loading content
* show an exception
* or even a custom view.  

The philosophy behind this library is to improve the UX through informing the user about what's happening behind, if the data is loading or an exception is thrown while fetching data...

Screenshots
---
 
<img src="https://raw.github.com/medyo/dynamicbox/master/screenshots/demo_popcorn_loading.png" alt="Popcorn Screen shot" width="250"> <img src="https://raw.github.com/medyo/dynamicbox/master/screenshots/demo_gplay_no_internet.png" alt="Google play screen shot" width="250"> <img src="https://raw.github.com/medyo/dynamicbox/master/screenshots/demo_slack_loading.png" alt="Slack screen shot" width="250"> <img src="https://raw.github.com/medyo/dynamicbox/master/screenshots/demo_gmail_no_messages.png" alt="Gmail screen shot" width="250">

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
- `RecyclerView`
- `ViewGroup`
- `or any view type overriding from one of these`

Including in your project : Maven Central
---------------------

```compile 'com.github.medyo:dynamicbox:1.2@aar'```

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

**To Override Strings**
Please refer to [strings.xml variables](DynamicBox/src/main/res/values/strings.xml)

**To Override Default style**
Please refer to [styles.xml](DynamicBox/src/main/res/values/styles.xml)

**To Override Default Layouts**
Please refer to [res/layouts](DynamicBox/src/main/res/layout/)

**See the example project for more details** [Sample](DynamicBoxExample/src/main/java/mehdi/sakout/dynamicboxexample/)

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
