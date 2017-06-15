import Vue from "vue";
import App from "./App.vue";

export default () => {
    new Vue({
        el: 'body',
        render: h => h(App),
        components: {App}
    });
}