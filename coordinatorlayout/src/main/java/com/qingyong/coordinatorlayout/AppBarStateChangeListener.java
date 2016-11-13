package com.qingyong.coordinatorlayout;

import android.support.design.widget.AppBarLayout;

/**
 * <b>Project:</b> com.qingyong.coordinatorlayout <br>
 * <b>Create Date:</b> 2016/11/13 <br>
 * <b>Author:</b> Devin <br>
 * <b>Address:</b> qingyong@linghit.com <br>
 * <b>Description:</b> CollapsingToolbarLayout展开和收缩的监听 <br>
 * </b> {#http://stackoverflow.com/questions/31682310/android-collapsingtoolbarlayout-collapse-listener} <br>
 * </b> http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0619/4362.html <br>
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
