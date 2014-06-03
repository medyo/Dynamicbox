package mehdi.sakout.dynamicbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ViewSwitcher;

public class DynamicBox {

	private View mTargetView;
	private View.OnClickListener mClickListener;
	private Context mContext;
	private LayoutInflater mInflater;
	private RelativeLayout mContainer;
	private ArrayList<View> mCustomViews;
	private ArrayList<View> mDefaultViews;
    private  ViewSwitcher mSwitcher;

    // Default Tags
	private final String TAG_INTERNET_OFF 	 =  "INTERNET_OFF";
	private final String TAG_LOADING_CONTENT =  "LOADING_CONTENT";
	private final String TAG_OTHER_EXCEPTION =  "OTHER_EXCEPTION";
	
	// Default Strings
	private final String MESSAGE_LOADING	 =	"Please wait...";
    private final String TITLE_LOADING	     =	"Loading";

	private final String MESSAGE_NO_INTERNET = "Internet is off, please enable it";
    private final String TITLE_NO_INTERNET   = "Error";


	private final String MESSAGE_FAILURE 	 = "An error has occurred, retry again";
    private final String TITLE_FAILURE 	     = "Error";

    private final String[] mSupportedAbsListViews = new String[]{"listview","gridview","expandablelistView"};
    private final String[] mSupportedViews = new String[]{"linearlayout","relativelayout","scrollview"};

	public DynamicBox(Context context, View targetView){
		this.mContext 		= context;
		this.mInflater 		= ((Activity)mContext).getLayoutInflater();
		this.mTargetView 	= targetView;
		this.mContainer 	= new RelativeLayout(mContext);
		this.mCustomViews 	= new ArrayList<View>();
		this.mDefaultViews	= new ArrayList<View>();

        String type = mTargetView.getClass().getName().substring(mTargetView.getClass().getName().lastIndexOf('.')+1).toLowerCase(Locale.getDefault());

		if(Arrays.asList(mSupportedAbsListViews).contains(type))
            initializeAbsListView();
		else if(Arrays.asList(mSupportedViews).contains(type))
            initializeViewContainer();
        else
		    throw new IllegalArgumentException("TargetView type is not supported !");

	}

    public DynamicBox(Context context,int viewID){
        this.mContext 		= context;
        this.mInflater 		= ((Activity)mContext).getLayoutInflater();
        this.mTargetView 	= mInflater.inflate(viewID, null, false);
        this.mContainer 	= new RelativeLayout(mContext);
        this.mCustomViews 	= new ArrayList<View>();
        this.mDefaultViews	= new ArrayList<View>();

        String type = mTargetView.getClass().getName().substring(mTargetView.getClass().getName().lastIndexOf('.')+1).toLowerCase(Locale.getDefault());

        if(Arrays.asList(mSupportedAbsListViews).contains(type))
            initializeAbsListView();
        else if(Arrays.asList(mSupportedViews).contains(type))
            initializeViewContainer();
        else
            throw new IllegalArgumentException("ViewId type is not supported !");

    }

    private void initializeViewContainer(){

        setDefaultViews();

        mSwitcher = new ViewSwitcher(mContext);
        ViewSwitcher.LayoutParams params = new ViewSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mSwitcher.setLayoutParams(params);

        mSwitcher.addView(mContainer,0);
        mSwitcher.addView(mTargetView,1);

        mSwitcher.setDisplayedChild(1);

        ((Activity)mContext).setContentView(mSwitcher);
    }

    private void setDefaultViews(){
        View mLayoutInternetOff = initView(R.layout.exception_no_internet,TAG_INTERNET_OFF,TITLE_NO_INTERNET,MESSAGE_NO_INTERNET);
        View mLayoutLoadingContent = initView(R.layout.exception_loading_content,TAG_LOADING_CONTENT,TITLE_LOADING,MESSAGE_LOADING);
        View mLayoutOther = initView(R.layout.exception_failure,TAG_OTHER_EXCEPTION,TITLE_FAILURE,MESSAGE_FAILURE);

        mDefaultViews.add(0,mLayoutInternetOff);
        mDefaultViews.add(1,mLayoutLoadingContent);
        mDefaultViews.add(2,mLayoutOther);

        // Hide all layouts at first initialization
        mLayoutInternetOff.setVisibility(View.GONE);
        mLayoutLoadingContent.setVisibility(View.GONE);
        mLayoutOther.setVisibility(View.GONE);

        // init Layout params
        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        containerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        containerParams.addRule(RelativeLayout.CENTER_VERTICAL);

        // init new RelativeLayout Wrapper
        mContainer.setLayoutParams(containerParams);

        // Add default views
        mContainer.addView(mLayoutLoadingContent);
        mContainer.addView(mLayoutInternetOff);
        mContainer.addView(mLayoutOther);
    }

	private void initializeAbsListView(){

        setDefaultViews();

        AbsListView abslistview = (AbsListView)mTargetView;
        abslistview.setVisibility(View.GONE);
		ViewGroup parent = (ViewGroup) abslistview.getParent();
        if(mContainer!=null){
            parent.addView(mContainer);
            abslistview.setEmptyView(mContainer);
        }else
            throw new IllegalArgumentException("mContainer is null !");

	}
	
	public void showLoadingLayout(){
		show(TAG_LOADING_CONTENT);
	}
	public void showInternetOffLayout(){
		show(TAG_INTERNET_OFF);
	}
	public void showExceptionLayout(){
		show(TAG_OTHER_EXCEPTION);
	}
	public void showCustomView(String tag){
        show(tag);
    }

    public void hideAll(){
        ArrayList<View> views =  new ArrayList<View>(mDefaultViews);
        views.addAll(mCustomViews);
        for(View view : views){
            view.setVisibility(View.GONE);
        }
        if(mSwitcher!=null && mSwitcher.getDisplayedChild()!=1){
            mSwitcher.setDisplayedChild(1);
        }
    }
	private void show(String tag){
        ArrayList<View> views =  new ArrayList<View>(mDefaultViews);
        views.addAll(mCustomViews);
		for(View view : views){
			if(view.getTag()!=null && view.getTag().toString().equals(tag)){
				view.setVisibility(View.VISIBLE);
			}else{
				view.setVisibility(View.GONE);
			}
		}
        if(mSwitcher!=null && mSwitcher.getDisplayedChild()!=0){
            mSwitcher.setDisplayedChild(0);
        }
	}
	/**
	 * Return a view based on layout id
	 * @param layout Layout Id
	 * @param tag Layout Tag
	 * @return View
	 */
	private View initView(int layout, String tag, String title, String description){
		View view = mInflater.inflate(layout, null,false);

        view.setTag(tag);
		view.setVisibility(View.GONE);
		
		((TextView)view.findViewById(R.id.exception_title)).setText(title);
		((TextView)view.findViewById(R.id.exception_message)).setText(description);
        View buttonView = view.findViewById(R.id.exception_button);
        if(buttonView!=null)
            buttonView.setOnClickListener(this.mClickListener);

		return view;
	}
    public void setLoadingMessage(String message){
        ((TextView)mDefaultViews.get(1).findViewById(R.id.exception_message)).setText(message);
    }
    public void setInternetOffMessage(String message){
        ((TextView)mDefaultViews.get(0).findViewById(R.id.exception_message)).setText(message);
    }
    public void setOtherExceptionMessage(String message){
        ((TextView)mDefaultViews.get(2).findViewById(R.id.exception_message)).setText(message);
    }
    public void setInternetOffTitle(String message){
        ((TextView)mDefaultViews.get(0).findViewById(R.id.exception_title)).setText(message);
    }
    public void setOtherExceptionTitle(String message){
        ((TextView)mDefaultViews.get(2).findViewById(R.id.exception_title)).setText(message);
    }
    public void setClickListener(View.OnClickListener clickListener){

        this.mClickListener = clickListener;

        for(View view : mDefaultViews){
            View buttonView = view.findViewById(R.id.exception_button);
            if(buttonView!=null)
                buttonView.setOnClickListener(this.mClickListener);
        }


    }

    public void addCustomView(View customView,String tag){

        customView.setTag(tag);
        customView.setVisibility(View.GONE);
        mCustomViews.add(customView);
        mContainer.addView(customView);
    }
}
