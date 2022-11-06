import { configureStore } from "@reduxjs/toolkit"
import userReducer from "./slice/authSlice"

const rootReducer = {
    user: userReducer
}

const store = configureStore({
    reducer: rootReducer,

});

export default store;