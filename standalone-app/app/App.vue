<template>
    <div id="app">
        <p>{{ message }}</p>

        <a id="fancy-btn" class="waves-effect waves-light btn"><i class="material-icons left">cloud</i>button</a>
    </div>
</template>

<script>
    export default {
        data () {
            return {
                message: "Hallo Vue"
            }
        }
    }
</script>
