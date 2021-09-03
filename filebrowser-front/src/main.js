import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
import Store from './store/store'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

const store = createStore(Store);
const app = createApp(App);
app.use(store);
app.mount('#app');
