<template>
    <div>
        <nav id="cal-nav">
            <span class="left">{{ currentDate }}</span>
            <ul class="right">
                <li><a v-on:click="today" class="btn">Today</a></li>
                <li><a v-on:click="prev" class="btn"><i class="material-icons">chevron_left</i></a></li>
                <li><a v-on:click="next" class="btn"><i class="material-icons">chevron_right</i></a></li>
            </ul>
        </nav>

        <div id="calendar"></div>
    </div>
</template>

<script>
    import $ from "jquery"

    let cal;

    const data = {
        message: "Page 1",
        currentDate: ''
    };

    function today() {
        cal.fullCalendar('today');
    }

    function prev() {
        cal.fullCalendar('prev');
    }

    function next() {
        cal.fullCalendar('next');
    }

    function viewRender(view, element) {
        data.currentDate = cal.fullCalendar('getDate').format('LL');
    }

    function initCalendar() {
        cal = $('#calendar');
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
            ],

            viewRender
        });
    }

    export default {
        data: () => data,
        methods: {
            today: today,
            prev: prev,
            next: next
        },
        mounted() {
            initCalendar();
        }
    }
</script>

<style type="text/css">

    /* reset materialize navbar appearance but use the formatting */
    #cal-nav {
        color: inherit;
        background-color: inherit;
        border: inherit;
        box-shadow: none;
    }

    #cal-nav span {
        margin-left: 5px;
        font-size: 20px;
    }

</style>
