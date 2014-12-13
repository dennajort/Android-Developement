package fr.dennajort.fibonacciclient;

import android.view.View;
import android.view.ViewParent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Baptiste on 13/12/2014.
 */

public class GRadioGroup {
    private List<RadioButton> radios = new ArrayList<>();
    private int currentId = -1;

    public GRadioGroup(RadioButton... radios) {
        super();

        for (RadioButton rb : radios) {
            this.radios.add(rb);
            rb.setOnClickListener(onClick);
            if (rb.isChecked() && currentId == -1)
                currentId = rb.getId();
            else if (rb.isChecked())
                rb.setChecked(false);
        }
    }

    public int getCurrentId() {
        return currentId;
    }

    View.OnClickListener onClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            for (RadioButton rb : radios) {
                ViewParent p = rb.getParent();
                if (p.getClass().equals(RadioGroup.class)) {
                    RadioGroup rg = (RadioGroup) p;
                    rg.clearCheck();
                } else {
                    rb.setChecked(false);
                }
            }

            if (v.getClass().equals(RadioButton.class)) {
                RadioButton rb = (RadioButton) v;
                rb.setChecked(true);
                currentId = rb.getId();
            }

        }
    };
}
