package com.example.cuteweatherv1.repository.air

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.ui.air.FragmentAirInfo

private val TAB_TITLES = arrayOf(
    R.string.tab_text_7,
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4,
    R.string.tab_text_5,
    R.string.tab_text_6,
    R.string.today,
    R.string.tomorrow
)
class AirSectionsAdapter (private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return FragmentAirInfo.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val c = ChangeToWeek()
        val week = c.toWeek()
        if (position == 0) {
            return context.resources.getString(TAB_TITLES[7])
        } else if (position ==1) {
            return context.resources.getString(TAB_TITLES[8])
        } else if (position + week <= 7) {
            return context.resources.getString(TAB_TITLES[position+week-1])
        } else{
            return context.resources.getString(TAB_TITLES[position+week-8])
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 5
    }


}