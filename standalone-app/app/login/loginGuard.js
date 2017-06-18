import HTTP from "../http";

export default function (to, from, next) {

    // routing to login is always ok
    if (to.path === '/login') {
        next();
        return;
    }

    console.log(HTTP.defaults.auth);
    // if we have credentials, go on...otherwise to login
    if (HTTP.defaults.auth) {
        next();
    } else {
        next({path: '/login', query: {redirect: to.path}});
    }
}