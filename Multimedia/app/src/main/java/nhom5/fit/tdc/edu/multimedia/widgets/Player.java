package nhom5.fit.tdc.edu.multimedia.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import nhom5.fit.tdc.edu.multimedia.R;

public class Player extends LinearLayout{
    private ImageView btPrev , btPlay, btPause, btStop, btNext;
    private ViewGroup playerlayout;
    private PlayerDelegation delegate;
    private OnClickListener onClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.isSelected()){
                v.setSelected(false);
            }
            else {
                int count = playerlayout.getChildCount();
                for(int i = 0; i < count; i++ ){
                    playerlayout.getChildAt(i).setSelected(false);
                }
                v.setSelected(true);

            }

            if(delegate == null){
                Toast.makeText(getContext(), "toast", Toast.LENGTH_LONG).show();
            }else{
                switch (v.getId()){
                    case R.id.next:
                        delegate.next();
                        break;
                    case R.id.pause:
                        delegate.pause();
                        break;
                    case R.id.stop:
                        delegate.stop();
                        break;
                    case R.id.play:
                        delegate.play();
                    case R.id.prev:
                        delegate.prev();
                }
            }
        }
    };

    public Player(Context context) {
        super(context);
        intialization();
    }
    public Player(Context context, AttributeSet attrs) {
        super(context, attrs);
        intialization();
    }

    public Player(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intialization();
    }

    private void intialization(){
        inflate(getContext(), R.layout.layout_music,this);
        playerlayout = (ViewGroup)getChildAt(0);

        btPrev = playerlayout.findViewById(R.id.prev);
        btPrev.setOnClickListener(onClick);
        btPlay = playerlayout.findViewById(R.id.play);
        btPlay.setOnClickListener(onClick);
        btPause = playerlayout.findViewById(R.id.pause);
        btPause.setOnClickListener(onClick);
        btStop = playerlayout.findViewById(R.id.stop);
        btStop.setOnClickListener(onClick);
        btNext = playerlayout.findViewById(R.id.next);
        btNext.setOnClickListener(onClick);
    }

    public void setDelegate(PlayerDelegation delegate) {
        this.delegate = delegate;
    }
}
