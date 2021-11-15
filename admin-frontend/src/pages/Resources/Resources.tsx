import React, { useEffect, useState } from 'react';
import { getResourcesList } from './Resources.api';
import { Resource } from './types';
import { IconButton, makeStyles, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@material-ui/core/';
import {Delete} from '@material-ui/icons'
import { Table } from 'react-bootstrap';

// interface ResourcesProps {
//   Resource: any

// }

// const Resources: React.FC<ResourcesProps> = ({Resource}) => {

// }

const useStyles = makeStyles({
  tableRow: {
    cursor: 'pointer'
  },
  textField: {
    marginBottom: '16px'
  }
})

function Resources() {
  const classes = useStyles();
  const [resources, setResource] = useState<Resource[] | null>(null)
  useEffect(() => {
    const fetchResourceList = async () => {
      const resourceList = await getResourcesList()
      console.log(resourceList)
      setResource(resourceList)
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
        <TableCell>Actions</TableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {resources && resources.map(resource => (
        <TableRow className={classes.tableRow} hover key={resource.id}>
          <TableCell>
            {resource.id}
          </TableCell>
          <TableCell>
            {resource.resourceName}
          </TableCell>
          <TableCell>
            {resource.resourceLink}
          </TableCell>
          <TableCell>
            <IconButton>
              <Delete />
            </IconButton>
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </Table>
  </TableContainer>
  <Typography variant="h3">Add Resources</Typography>
  <form>
    <TextField className={classes.textField} name="ResourceName" value='' label="Resource Name" placeholder="ResourceName" />
    <TextField className={classes.textField} name="ResourceLink" value='' label="Resource Link" placeholder="ResourceLink"/>
  </form>
  </>  
  );
}

export default Resources;