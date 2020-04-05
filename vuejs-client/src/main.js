import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import { ValidationProvider, extend } from 'vee-validate';
import { required } from 'vee-validate/dist/rules';
import {library} from '@fortawesome/fontawesome-svg-core'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
  faHome
} from '@fortawesome/free-solid-svg-icons'

library.add(
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
  faHome
)

Vue.config.productionTip = false

extend('required', required);

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('ValidationProvider', ValidationProvider)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
