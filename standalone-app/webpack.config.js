var path = require('path');
var webpack = require('webpack');

module.exports = {
    entry: './app/main.js',
    output: {
        path: path.resolve(__dirname, './dist'),
        filename: 'build.js'
    },
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                options: {
                    presets: ['electron']
                }
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.(woff|woff2|eot|ttf)$/,
                loader: 'url-loader'
            }
        ]
    }/*,
     plugins: [
     new webpack.ExternalsPlugin('commonjs', [
     'electron'
     ])
     ]*/
};
