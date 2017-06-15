export default () => {
    window.$ = window.jQuery = require('jquery');

    require('fullcalendar/dist/fullcalendar.min.css');
    require('fullCalendar');
}