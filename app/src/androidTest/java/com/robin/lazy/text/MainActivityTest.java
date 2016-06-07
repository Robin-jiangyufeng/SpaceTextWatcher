package com.robin.lazy.text;

import android.support.test.espresso.core.deps.guava.util.concurrent.Uninterruptibles;
import android.test.ActivityUnitTestCase;
import android.widget.EditText;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 文 件 名:  MainActivityTest.java
 * 版    权:  Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  江钰锋 00501
 * 修改时间:  16/6/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

public class MainActivityTest extends ActivityUnitTestCase<MainActivity>{

    private EditText[] editTexts=new EditText[3];

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testStart() {
        editTexts[0]=(EditText)getActivity().findViewById(R.id.editText);//银行卡
        editTexts[1]=(EditText)getActivity().findViewById(R.id.editText2);//手机号
        editTexts[2]=(EditText)getActivity().findViewById(R.id.editText3);//身份证
        editTexts[0].performClick();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        editTexts[1].performClick();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        editTexts[2].performClick();
    }
}