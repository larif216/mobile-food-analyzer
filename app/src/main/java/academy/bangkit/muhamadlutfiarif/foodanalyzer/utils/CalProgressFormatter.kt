package academy.bangkit.muhamadlutfiarif.foodanalyzer.utils

import com.dinuscxj.progressbar.CircleProgressBar

class CalProgressFormatter: CircleProgressBar.ProgressFormatter {
    override fun format(p0: Int, p1: Int): CharSequence {
        return String.format("%d" + System.getProperty("line.separator") + "cals left", p1-p0)
    }
}