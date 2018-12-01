
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.view.View
import example.R
import example.BaseVMActivity
import example.ActivitySelectedBinding

class SelectedActivity : BaseVMActivity<SelectedActivityViewModel, ActivitySelectedBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_selected
    }

    override fun isInjectable(): Boolean {
        return true
    }

    override fun getViewModelClass(): Class<SelectedActivityViewModel> = SelectedActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra(SelectedActivityUtils.FRAGMENT_NAME_TAG)
        var title = getString(R.string.app_name)
        if (intent.hasExtra(SelectedActivityUtils.FRAGMENT_TITLE_TAG))
            title = intent.getStringExtra(SelectedActivityUtils.FRAGMENT_TITLE_TAG)
        else if (intent.hasExtra(SelectedActivityUtils.FRAGMENT_TITLE_RES_TAG))
            title = getString(intent.getIntExtra(SelectedActivityUtils.FRAGMENT_TITLE_RES_TAG, 0))
        if (intent.hasExtra(SelectedActivityUtils.MENU_TAG)) {
            menuId = intent.getIntExtra(SelectedActivityUtils.MENU_TAG, 0)
            invalidateOptionsMenu()
        }

        val fragment = Fragment.instantiate(this, name, intent.extras)
        binding.textTitle.text = title
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (!intent.getBooleanExtra(SelectedActivityUtils.SHOW_TOOLBAR_TAG, true))
            binding.appBar.visibility = View.GONE

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(binding.container.id, fragment)
                    .commit()

        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
