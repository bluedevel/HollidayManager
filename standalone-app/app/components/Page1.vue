<template>
    <div>
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
            $('#calendar').fullCalendar({});
        }
    }
</script>