import Vue from "vue";
import VueRouter from "vue-router";
import App from "./App.vue";
import routes from "./routes";

export default () => {
    Vue.use(VueRouter);

    const router = new VueRouter({routes});

    new Vue({
        el: 'body',
        render: h => h(App),
        router,
        components: {App}
    });
}