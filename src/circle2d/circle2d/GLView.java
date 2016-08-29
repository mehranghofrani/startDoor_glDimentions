package circle2d.circle2d;

import java.lang.Math;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.widget.LinearLayout;
import android.widget.RemoteViews.ActionException;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.PowerManager;
import utils.*;


public class GLView extends GLSurfaceView implements Renderer ,OnTouchListener{

	public static GL10 gl;
	public boolean homeBtn=false;
    public float homeBtnC;
    public Float firstX=null;
    public Float firstY=null;
    public boolean p1=false,p2=false;
    public Point p1c,p2c;
    public Activity parentAct;
    public line aaaaa;
    public circle a;
    float distanceFromKey;


	public GLView(Context context) {
		super(context);
		parentAct=(Activity)context;
		setRenderer(this);
		setOnTouchListener(this);
	}
	public void onDrawFrame(GL10 gl) {

		gl.glClearColor(1-(homeBtnC-1/5f), 1-(homeBtnC-1/5f), 1-(homeBtnC-1/5f),1-(homeBtnC-1/5f));
		gl.glClear(gl.GL_COLOR_BUFFER_BIT);



		a.draw(gl);






	}
	public void onSurfaceChanged(GL10 arg0, int arg1, int arg2) {

	}
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		gl.glClearColor(1, 1, 1, 0);
		gl.glClear(gl.GL_COLOR_BUFFER_BIT);
		homeBtnC=1/5f;
		a=new circle(0, -1, homeBtnC, (short)128);
		aaaaa=new line(-1, -1, 0, 1, 1, 0);
		gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
		GLU.gluOrtho2D(gl, -(float)getWidth()/getHeight(),(float)getWidth()/getHeight(), -1, +1);
		gl.glColor4f(0, 0, 0, 0);
		gl.glClearColor(1, 1, 1, 0);
	}
	public boolean onTouch(View v, MotionEvent event) {

		if(event.getAction()==event.ACTION_MOVE){





			if(homeBtn){
				double screenSize=Math.sqrt(Math.pow(getHeight(), 2)+Math.pow(getWidth(), 2));
				homeBtnC+=(firstY-event.getY())/screenSize*4;
				homeBtnC+=(event.getX()-firstX)*Math.signum(firstX/getWidth()-0.5)/screenSize*4;




				a=new circle(0, -1, homeBtnC, (short)128);

				if(homeBtnC<=1/5f)
					homeBtnC=1/5f;



				if(homeBtnC>=6/5f)
					parentAct.setContentView(new DrawView(parentAct));

			}
			if(p2==true){
//				p2c.x=event.getX();
//				p2c.y=event.getY();
			}
			else{
//				p1c.x=event.getX();
//				p1c.y=event.getY();


			}

			firstX=event.getX();
			firstY=event.getY();
		}

		if(event.getAction()==event.ACTION_DOWN){
			firstX=event.getX();
			firstY=event.getY();
			distanceFromKey=(float)Math.sqrt(
					Math.pow(this.getWidth()/2-event.getX(),2)+
					Math.pow(this.getHeight()-event.getY(),2)
					);
			if(distanceFromKey<getHeight()*homeBtnC/2){
				homeBtn=true;
//				parentAct.setContentView(new DrawView(parentAct));

			}
			else{
				homeBtn=false;

				if(p2)
					p1=p2=false;
				if(p1)
					p2=true;
				else
					p1=true;
			}
		}


		if(event.getAction()==event.ACTION_UP){




		}

		return true;
	}

}



