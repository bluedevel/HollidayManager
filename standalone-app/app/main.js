import Vue from "vue";
import App from "./App.vue";

window.$ = window.jQuery = require('jquery');

new Vue({
    el: 'body',
    render: h => h(App),
    components: {App}
});
