import React from 'react';

import Campaign from './Campaign';
import Menu from '../Menu';
import Search from '../Search';

const Campaigns = ({campaigns, matched, onEdit, onDelete, sortByEndDate, sortByPrice, sortByName, sortByStore, matchItem, cancelMatch, showMatchItems, showAllCampaigns, setQuery}) => {

    return (
        <>
            <Search matchItem={matchItem} cancelMatch={cancelMatch} showMatchItems={showMatchItems} showAllCampaigns={showAllCampaigns} setQuery={setQuery}/>
            <Menu sortByEndDate={sortByEndDate} sortByPrice={sortByPrice} sortByName={sortByName} sortByStore={sortByStore}/>
            {campaigns.length > 0 ? 
            (<table className="campaign-table" cellSpacing="0" cellPadding="0">
                <thead>
                    <tr>
                        <td colSpan="1">Name</td>
                        <td colSpan="1">Store</td>
                        <td colSpan="1">Amount</td>
                        <td colSpan="1">Price</td>
                        <td colSpan="1">Start date</td>
                        <td colSpan="1">End date</td>
                        <td colSpan="1"></td>
                    </tr>
                </thead>
                <tbody>
                {campaigns.map((campaign) => <Campaign key={campaign.campaignId} campaign={campaign} matched={matched} onEdit={onEdit} onDelete={onDelete}/>)}
                </tbody>
            </table>) 
            : 
            <p className="blank-text">There is nothing here.</p>}
            
        </>
    )
}

export default Campaigns
