<template>
    <div id="app">
        <nav>
            <div class="nav-wrapper">
                <ul class="">
                    <li><router-link to="/page1">Page 1</router-link></li>
                    <li><router-link to="/page2">Page 2</router-link></li>
                    <li><router-link to="/page3">Page 3</router-link></li>
                </ul>
            </div>
        </nav>
        <router-view></router-view>
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
