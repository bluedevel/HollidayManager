import Login from "./login/Login.vue";
import Main from "./main/Main.vue";
import Overview from "./main/components/Overview.vue";
import Page2 from "./main/components/Page2.vue";
import Page3 from "./main/components/Page3.vue";

export default [
    {path: "/", redirect: "/main/overview"},
    {path: "/login", component: Login},
    {
        path: "/main", component: Main,
        children: [
            {path: "overview", component: Overview},
            {path: "page2", component: Page2},
            {path: "page3", component: Page3}
        ]
    },
]