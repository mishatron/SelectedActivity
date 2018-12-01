
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment

object SelectedActivityUtils {
    const val FRAGMENT_NAME_TAG = "FRAGMENT_NAME"
    const val FRAGMENT_TITLE_TAG = "FRAGMENT_TITLE"
    const val SHOW_TOOLBAR_TAG = "SHOW_TOOLBAR"
    const val FRAGMENT_TITLE_RES_TAG = "FRAGMENT_TITLE_RES"
    const val MENU_TAG = "MENU_ID"

    class Builder(private val context: Activity?, fragmentName: String) {
        private val intent = Intent(context, SelectedActivity::class.java)
                .putExtra(FRAGMENT_NAME_TAG, fragmentName)

        fun showToolbar(b: Boolean): Builder {
            intent.putExtra(SHOW_TOOLBAR_TAG, b)
            return this
        }

        fun setTitle(t: String): Builder {
            intent.putExtra(FRAGMENT_TITLE_TAG, t)
            return this
        }

        fun setTitle(@StringRes id: Int): Builder {
            intent.putExtra(FRAGMENT_TITLE_RES_TAG, id)
            return this
        }

        fun setBundle(bundle: Bundle): Builder {
            intent.putExtras(bundle)
            return this
        }

        fun setMenu(@MenuRes id: Int): Builder {
            intent.putExtra(MENU_TAG, id)
            return this
        }

        fun start() {
            context?.startActivity(intent)
        }

        fun startForResult(code: Int) {
            context?.startActivityForResult(intent, code)
        }

        fun startForResultForFragment(fragment: Fragment?, code: Int) {
            fragment?.startActivityForResult(intent, code)
        }
    }
}
