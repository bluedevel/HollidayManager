<template>
    <div id="calendar">{{ message }}</div>
</template>

<script>
    import $ from "jquery"

    $(document).ready(() => {

    });

    export default {
        data() {
            return {
                message: "Page 1"
            }
        },

        mounted() {
            $('#calendar').css('color', 'red');
        }
    }
</script>