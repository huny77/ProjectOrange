import Vue from "vue";
import App from "./App.vue";
import router from "@/router";
import vuetify from "./plugins/vuetify";
import store from '@/store/index'
import './plugins';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import axios from 'axios'
import vueMoment from 'vue-moment'

Vue.use(vueMoment)
Vue.use(BootstrapVueIcons)
Vue.use(BootstrapVue)

Vue.config.productionTip = false;

Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
