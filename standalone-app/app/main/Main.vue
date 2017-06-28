<template>
    <div id="app">
        <nav>
            <div class="nav-wrapper">
                <ul class="left">
                    <li>
                        <router-link to="overview">Overview</router-link>
                    </li>
                    <li>
                        <router-link to="testLogin">Test Login</router-link>
                    </li>
                    <li>
                        <router-link to="page3">Page 3</router-link>
                    </li>
                </ul>
                <ul class="right">
                    <router-link to="profile"><i class="material-icons">face</i></router-link>
                </ul>
            </div>
        </nav>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {}
</script>

<style>

    .right {
        margin-right: 20px;
    }

</style>