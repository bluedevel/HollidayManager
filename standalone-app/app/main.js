import Vue from "vue";
import App from "./App.vue";

window.$ = window.jQuery = require('jquery');

require('material-icons-font/material-icons-font.css');
require('materialize-css/dist/js/materialize.min');
require('materialize-css/dist/css/materialize.min.css');

$(document).ready(() => {
    new Vue({
        el: 'body',
        render: h => h(App),
        components: {App}
    });
});
