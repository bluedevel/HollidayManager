export default () => {
    window.$ = window.jQuery = require('jquery');

    require('fullcalendar/dist/fullcalendar.css');
    require('fullcalendar');
}