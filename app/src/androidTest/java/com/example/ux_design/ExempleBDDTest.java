package com.example.ux_design;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.ux_design.UI.ExempleBDD;
import com.example.ux_design.UI.MainActivity;




import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExempleBDDTest {

    @Rule
    public ActivityTestRule<ExempleBDD> rule  = new  ActivityTestRule<>(ExempleBDD.class);


    @Test
        public void test() {
            ExempleBDD activity =  rule.getActivity();
        }
    }


