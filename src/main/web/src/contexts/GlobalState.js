import React, {createContext, useReducer} from 'react'
import AppReducer from './AppReducer'

const initialState = {
    campaigns: []
}

export const GlobalContext = createContext(initialState);

export const GlobalProvider = ({children}) => {
    const [state, dispatch] = useReducer(AppReducer, initialState, children);

    function deleteCampaign(id) {
        dispatch({
            type: 'DELETE_CAMPAIGN',
            payload: id
        })
    }

    function addCampaign(campaign) {
        dispatch({
            type: 'ADD_CAMPAIGN',
            payload: campaign
        })
    }

    return (
        <GlobalContext.Provider value={{campaigns: state.campaigns, deleteCampaign, addCampaign}}>
            {children}    
        </GlobalContext.Provider>
    )
}




const GlobalState = () => {
    return (
        <div>
            
        </div>
    )
}

export default GlobalState
