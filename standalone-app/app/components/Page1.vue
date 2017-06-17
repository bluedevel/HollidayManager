<template>
    <div>
        <a class="waves-effect waves-light btn">Test</a>
        <p id="message">{{ message }}</p>
        <div id="calendar"></div>
    </div>
</template>

<script>
    import $ from "jquery"

    export default {
        data() {
            return {
                message: "Page 1"
            }
        },

        mounted() {
            const cal = $('#calendar');
            cal.fullCalendar({
                editable: true,
                displayEventTime: true,
                header: false,
                events: [
                    {
                        title: 'This is a Material Design event!',
                        start: new Date(),
                        end: new Date(),
                        color: '#C2185B'
                    }
                ]
            });

            // tinker with the style to make the buttons materialize
            // nativ fullcalendar classes: fc-next-button fc-button fc-state-default fc-corner-right btn
            /*cal.find('button')
                .addClass('waves-effect waves-light btn')
                .removeClass('fc-next-button')
                .removeClass('fc-button')
                .removeClass('fc-state-default')
                .removeClass('fc-corner-right');*/

        }
    }
</script>