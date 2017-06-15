import bootJQuery from "./bootstrapJQuery";
import bootMaterialize from "./bootstrapMaterialize";
import bootVue from "./bootstrapVue";

bootJQuery();
bootMaterialize();

$(document).ready(() => {
    bootVue();
});
