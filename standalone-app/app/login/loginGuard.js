import {remote} from 'electron'

export default function (to, from, next) {

    // routing to login is always ok
    if (to.path === '/login') {
        next();
        return;
    }

    remote.session.defaultSession.cookies.get({name: 'JSESSIONID'}, (error, cookies) => {
        if (error || !(cookies[0])) {
            next({path: '/login', query: {redirect: to.path}});
        } else {
            next();
        }
    });
}