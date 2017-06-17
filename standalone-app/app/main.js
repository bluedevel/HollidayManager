import bootJQuery from "./bootstrapJQuery";
import bootMaterialize from "./bootstrapMaterialize";
import bootVue from "./bootstrapVue";

bootJQuery();
bootMaterialize();

//require('./css/fullcalendarMaterial.scss');

$(document).ready(() => {
    bootVue();
});
