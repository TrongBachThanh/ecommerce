import { createSlice } from "@reduxjs/toolkit";

const user = createSlice({
    name: 'userInfo',
    initialState: {
        username: null,
        fullName: null,
        role: null
    },
    reducers: {
        addUser: (state, action) => {
            state.username = action.payload.username;
            state.fullName = action.payload.fullName;
            state.role = action.payload.role;
        },
        removeUser: (state) => {
            state.username = null;
            state.fullName = null;
            state.role = null;
        }

    }
});

const { reducer, actions } = user;

export const { addUser } = actions;

export default reducer;