import Vue from "vue";
import VueRouter from "vue-router"
import App from "./App.vue";

export default () => {
    Vue.use(VueRouter);

    new Vue({
        el: 'body',
        render: h => h(App),
        components: {App}
    });
}