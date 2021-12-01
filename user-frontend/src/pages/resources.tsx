// @flow
import { Resource } from './types';
import { getResourcesList, createResource, deleteResource } from './resources.api';
import { Box, IconButton, makeStyles, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@material-ui/core/';
import React, { FormEventHandler, useEffect, useState } from 'react';


const useStyles = makeStyles({
    tableRow: {
      cursor: 'pointer'
    },
    textField: {
      marginBottom: '16px',
      marginRight: '16px'
    }
  })


export const Resources = () => {
    const classes = useStyles();
    const [resources, setResources] = useState<Resource[] | null>(null)

    useEffect(() => {
        const fetchResourceList = async () => {
          const resourceList = await getResourcesList()
          console.log(resourceList)
          setResources(resourceList)
        }
        fetchResourceList()
      },[])

    return (<>
    <Typography variant="h3">Resources</Typography>
    <TableContainer>
  <Table>
    <TableHead>
      <TableRow>
        <TableCell>ID</TableCell>
        <TableCell>Resource Name</TableCell>
        <TableCell>Resource Link</TableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {resources && resources.map(resource => (
        <TableRow className={classes.tableRow} hover key={resource.resourceId}>
          <TableCell>
            {resource.resourceId}
          </TableCell>
          <TableCell>
            {resource.resourceName}
          </TableCell>
          <TableCell>
            {resource.resourceLink}
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </Table>
  </TableContainer>
    </>
    );
};