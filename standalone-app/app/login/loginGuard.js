import HTTP from "../http";

export default function (to, from, next) {

    // routing to login is always ok
    if (to.path === '/login') {
        next();
        return;
    }

    // if we have credentials, go on...otherwise to login
    if (HTTP.auth) {
        next();
    } else {
        next('/login');
    }
}