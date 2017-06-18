import Vue from "vue";
import VueRouter from "vue-router";
import App from "./App.vue";
import routes from "./routes";
import loginGuard from './login/loginGuard'

export default () => {
    Vue.use(VueRouter);

    const router = new VueRouter({routes});
    router.beforeEach(loginGuard);

    new Vue({
        el: 'app',
        render: h => h(App),
        router,
        components: {App}
    });
}