import Overview from "./components/Overview.vue";
import Page2 from "./components/Page2.vue";
import Page3 from "./components/Page3.vue";

export default [
    {path: "/", redirect: "/overview"},
    {path: "/overview", component: Overview},
    {path: "/page2", component: Page2},
    {path: "/page3", component: Page3}
]